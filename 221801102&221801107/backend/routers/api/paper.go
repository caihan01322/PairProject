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

// @Summary 搜索
// @Accept json
// @Produce  json
// @Param param body searchBody true "搜索参数"
// @Success 200 {string} json "{"code":200,"data":{"name":"","avatar":""},"msg":"ok"}"
// @Router /search [post]
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
			offset := conf.SearchPageSize * (body.Page - 1)
			end := offset + conf.SearchPageSize
			if end >= total {
				end = total
			}
			papers := models.GetPapersByStrID(ids[offset:end])
			data["list"] = papers
		}

		data["total"] = total
		data["page_size"] = conf.SearchPageSize
		data["page"] = body.Page

		code = http.StatusOK
		msg = http.StatusText(code)
	}

	utils.JSONOK(c, code, msg, data)
}
