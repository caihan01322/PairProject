class ResponseData():
    code = 200
    msg = "success"
    data = None

    def __init__(self, code, msg, data):
        self.code = code
        self.msg = msg
        self.data = data
