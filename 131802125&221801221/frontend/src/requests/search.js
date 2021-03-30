import axios from "axios";

let root = "/xjbs/api/v1/paper/"

const request = {
    search: async(data) => {
        let url = root + 'search';
        console.log(url)
        console.log({"get req": data});
        return await axios_get(url,data);
    },
}

function axios_get(url,data){
    return new Promise((resolve,reject) => {
        axios({
            method: 'get',
            url: url,
            params: data
        })
        .then(function(res){
            resolve(res.data);
        })
        .catch(function(err){
            reject(err);
        })
    })
}

function axios_post(url,data){
    return new Promise((resolve,reject) => {
        axios({
            method: 'post',
            url: url,
            data: data,
            transformRequest: [function (data) {
                let ret = ''
                for (let it in data) {
                ret += encodeURIComponent(it) + '=' + encodeURIComponent(data[it]) + '&'
                }
                return ret
            }],
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            }
        })
        .then(function(res){
            resolve(res.data);
        })
        .catch(function(err){
            reject(err);
        })
    })
}


export default request;