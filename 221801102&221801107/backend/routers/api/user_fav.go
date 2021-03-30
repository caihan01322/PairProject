package api

import (
	"backend/models"
	"backend/pkg/utils"
	"github.com/gin-gonic/gin"
	"net/http"
)

func GetUserFav(c *gin.Context) {
	offset, pageSize := utils.GetPage(c)
	code := http.StatusOK
	msg := http.StatusText(http.StatusOK)
	data := make(gin.H)

	user := c.MustGet("user").(*models.User)
	fav, total := models.GetUserFavs(user, offset, pageSize)
	data["total"] = total
	data["page_size"] = pageSize
	data["page"] = offset/pageSize + 1
	data["list"] = fav

	utils.JSONOK(c, code, msg, data)
}

func OpUserFav(c *gin.Context) {
	code := c.Query("code")
	paper := models.GetPaperByCode(code)
	user := c.MustGet("user").(*models.User)
	fav := models.GetUserFav(user, paper)
	if fav.ID <= 0 {
		models.AddUserFav(user, paper)
	} else {
		models.DeleteUserFav(&fav)
	}

	utils.JSONOK(c, http.StatusOK, http.StatusText(http.StatusOK), nil)
}

type editUserFavBody struct {
	Code    string `json:"code"`
	Title   string `json:"title"`
	Content string `json:"content"`
}

func EditUserFav(c *gin.Context) {
	code := http.StatusBadRequest
	var msg string

	body := editUserFavBody{}
	if err := c.ShouldBindJSON(&body); err != nil {
		msg = err.Error()
	} else {
		paper := models.GetPaperByCode(body.Code)
		user := c.MustGet("user").(*models.User)
		fav := models.GetUserFav(user, paper)
		fav.Title = body.Title
		fav.Content = body.Content
		models.EditUserFav(&fav)

		code = http.StatusOK
		msg = http.StatusText(code)
	}

	utils.JSONOK(c, code, msg, nil)
}
