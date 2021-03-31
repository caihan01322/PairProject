import axios from './axios'


/**
 * 用户api
 */
export class User {
    /**
     * 登录
     * @param email
     * @param password
     * @returns {*}
     */
    static login(email, password) {
        return axios.post("/user/login", {
            email,
            password
        })
    }

    /**
     * 注册
     * @param email
     * @param username
     * @param password
     * @returns {*}
     */
    static logout(email, username, password) {
        return axios.post("/user/register", {
            email,
            username,
            password
        })
    }

}

/**
 * 论文api
 */
export class Paper {
    /**
     * 获取论文列表
     * @param keyword
     * @returns {*}
     */
    static getList(keyword) {
        return axios.get(`/paper/getList?keyword=${keyword}`)
    }

    /**
     * 判断论文获取进度
     * @returns {*}
     */
    static getProcess() {
        return axios.get("/paper/getProcess")
    }

}
