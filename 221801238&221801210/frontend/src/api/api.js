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

    /**
     * 搜索结果换页
     * @param pageNum
     * @returns {*}
     */
    static page(pageNum) {
        return axios.get(`/paper/page?currentPage=${pageNum}`)
    }

}


/**
 * 收藏夹api
 */
export class Favorites {
    static getList() {
        return axios.get('/favorites/get')
    }

    static getPaperList(favorite_id, currentPage) {
        return axios.get(`/favorites/getPaperList?favorite_id=${favorite_id}&currentPage=${currentPage}`)
    }

    static delete(favorite_id) {
        return axios.post('/favorites/delete', {
            favorite_id
        })
    }

    static create(name) {
        return axios.post('/favorites/create', {
            name
        })
    }

    static insert(favorite_id, paper_id) {
        return axios.post('/favorites/insert', {
            favorite_id,
            paper_ids: [paper_id]
        })
    }

}

/**
 * 数据api
 */
export class Data {
    static getPaperSource(beg, end) {
        return axios.get(`/data/getPaperSource?beg=${beg}&end=${end}`)
    }

    static getTopTen() {
        return axios.get('/data/getTopTen')
    }

}