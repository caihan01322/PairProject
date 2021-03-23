package api

import (
	"backend/conf"
	"backend/crawler"
	"backend/models"
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
		msg = err.Error()
	} else {
		ids, total := crawler.Search(body.Search, body.Page)
		papers := models.GetPapersByID(ids)

		data["total"] = total
		data["page_size"] = conf.PageSize
		data["page"] = body.Page
		data["list"] = papers

		code = http.StatusOK
		msg = http.StatusText(code)
	}

	utils.JSONOK(c, code, msg, data)
}
