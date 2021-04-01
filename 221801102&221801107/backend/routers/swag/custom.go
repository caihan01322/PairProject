package swag

import "backend/models"

type res struct {
	Code int    `json:"code"`
	Msg  string `json:"msg"`
}
type resEmptyObj struct {
	res
	Data interface{} `json:"data"`
}

type resEmptyArr struct {
	res
	Data []interface{} `json:"data"`
}

type resLogin struct {
	res
	Name   string `json:"name"`
	Avatar string `json:"avatar"`
}

type resCloud struct {
	res
	Data []models.WordCloud `json:"data"`
}

type resChartWord struct {
	res
	Data []models.ChartWord `json:"data"`
}
