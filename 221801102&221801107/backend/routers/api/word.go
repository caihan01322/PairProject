package api

import (
	"backend/models"
	"backend/pkg/utils"
	"github.com/gin-gonic/gin"
	"net/http"
)

// @Summary 词云
// @Produce  json
// @Success 200 {object} swag.resCloud
// @Router /cloud [get]
func GetWordCloud(c *gin.Context) {
	cloud := models.GetWordCloud()
	utils.JSONOK(c, http.StatusOK, http.StatusText(http.StatusOK), cloud)
}

// @Summary 折线图
// @Produce  json
// @Success 200 {object} swag.resChartWord
// @Router /cloud [get]
func GetWords(c *gin.Context) {
	words := models.GetWords(c.Query("contributor"))
	utils.JSONOK(c, http.StatusOK, http.StatusText(http.StatusOK), words)
}
