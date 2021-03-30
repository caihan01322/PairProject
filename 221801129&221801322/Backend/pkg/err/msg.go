package err

var MsgFlags = map[int]string{
	SUCCESS:        "OK",
	ERROR:          "FAIL",
	INVALID_PARAMS: "请求参数错误",

	ERROR_NOT_EXIST_USER:   "用户不存在",
	ERROR_INVALID_PASSWORD: "用户密码错误",

	TITLE:     "题目",
	KEYWORD:   "关键词",
	ARTICLEID: "编号",
}

func GetMsg(code int) string {
	msg, ok := MsgFlags[code]
	if ok {
		return msg
	}

	return MsgFlags[ERROR]
}
