package api

import (
	"backend/models"
	"backend/pkg/utils"
	"github.com/gin-gonic/gin"
	"net/http"
)

func GetWordCloud(c *gin.Context) {
	cloud := models.GetWordCloud()
	utils.JSONOK(c, http.StatusOK, http.StatusText(http.StatusOK), cloud)
}

func GetWords(c *gin.Context) {
	words := models.GetWords(c.Query("contributor"))
	utils.JSONOK(c, http.StatusOK, http.StatusText(http.StatusOK), words)
}
