package api

import (
	"backend/conf"
	"backend/models"
	"backend/pkg/cache"
	"backend/pkg/utils"
	"github.com/gin-gonic/gin"
	"net/http"
)

type searchBody struct {
	Search []string `json:"s" binding:"required"`
	Page   int      `json:"p" binding:"required"`
}

func Search(c *gin.Context) {
	code := http.StatusBadRequest
	var msg string
	data := make(map[string]interface{})

	var body searchBody
	if err := c.ShouldBindJSON(&body); err != nil {
		// msg = err.Error()
	} else {
		ids := cache.GetSearch(body.Search)
		total := len(ids)

		if total == 0 {
			data["list"] = []interface{}{}
		} else {
			offset := conf.PageSize * (body.Page - 1)
			limit := conf.PageSize
			if offset+limit >= len(ids) {
				limit = len(ids) - offset
			}
			papers := models.GetPapersByStrID(ids[offset : offset+limit])
			data["list"] = papers
		}

		data["total"] = total
		data["page_size"] = conf.PageSize
		data["page"] = body.Page

		code = http.StatusOK
		msg = http.StatusText(code)
	}

	utils.JSONOK(c, code, msg, data)
}
