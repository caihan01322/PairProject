package api

import (
	"backend/conf"
	"backend/models"
	"backend/pkg/utils"
	"github.com/gin-gonic/gin"
	"github.com/unknwon/com"
	"net/http"
)

// @Summary 获取收藏
// @Produce  json
// @Param p query int true "页码"
// @Success 200 {object} swag.resEmptyObj
// @Router /fav [get]
func GetUserFav(c *gin.Context) {
	page := com.StrTo(c.Query("p")).MustInt()
	offset := conf.FavPageSize * (page - 1)
	code := http.StatusOK
	msg := http.StatusText(http.StatusOK)
	data := make(gin.H)

	user := c.MustGet("user").(*models.User)
	fav, total := models.GetUserFavs(user, offset, conf.FavPageSize)
	data["total"] = total
	data["page_size"] = conf.FavPageSize
	data["page"] = page
	data["list"] = fav

	utils.JSONOK(c, code, msg, data)
}

// @Summary 收藏/取消收藏
// @Produce  json
// @Param code query string true "论文编号"
// @Success 200 {object} swag.resEmptyObj
// @Router /op-fav [get]
func OpUserFav(c *gin.Context) {
	code := c.Query("code")
	paper := models.GetPaperByCode(code)
	user := c.MustGet("user").(*models.User)
	fav := models.GetUserFav(user, paper)
	if fav.ID <= 0 {
		models.AddUserFav(user, paper)
	} else {
		models.DeleteUserFav(fav)
	}

	utils.JSONOK(c, http.StatusOK, http.StatusText(http.StatusOK), nil)
}

type editUserFavBody struct {
	Code    string `json:"code"`
	Title   string `json:"title"`
	Content string `json:"content"`
}

// @Summary 编辑收藏
// @Accept json
// @Produce  json
// @Param param body editUserFavBody true "编辑参数"
// @Success 200 {object} swag.resEmptyObj
// @Router /ed-fav [post]
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
		models.EditUserFav(fav)

		code = http.StatusOK
		msg = http.StatusText(code)
	}

	utils.JSONOK(c, code, msg, nil)
}
