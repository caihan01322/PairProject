let root = "/api"

const request = {
    // 已有论文检索
    search: async (data) => {
        let url = root+'/user/search';
        console.log("get req:");
        console.log(data);
        // return {
        //     error: 0,
        //     total: 25,
        //     result: [
        //         {
        //             id: "000001",
        //             title: "测试论文标题1他比较长测试论文标题1",
        //             number: "10.13722/j.cnki.jrme.2020.1183",
        //             keyword: ['岩石力学', 'CO2致裂', '爆破'],
        //             meeting: ['CVPR'],
        //             author: ['作者1', '作者2'],
        //             link: "https://www.baidu.com"
        //         },
        //         {
        //             id: "000002",
        //             title: "测试论文标题2他比较长测试论文标题2",
        //             number: "10.13722/j.cnki.jrme.2020.1183",
        //             keyword: ['岩石力学', 'CO2致裂', '爆破'],
        //             meeting: ['CVPR','ICCV'],
        //             author: [],
        //             link: "https://www.baidu.com"
        //         }
        //     ]
        // }
        return await axios_get(url, data);
    },
    // 标题输入提示
    getTitleTips: async (data) => {
        let url = root+'/user/title/tip';
        console.log("get req:");
        console.log(data);
        // return {
        //     error: 0,
        //     result: [
        //         "相关标题1号可能可能可能可能可能可能会比较长",
        //         "相关标题2号可能可能可能可能可能可能会比较长",
        //         "相关标题3号可能可能可能可能可能可能会比较长",
        //         "相关标题4号可能可能可能可能可能可能会比较长",
        //         "相关标题5号可能可能可能可能可能可能会比较长"
        //     ]
        // }
        return await axios_get(url, data);
    },
    // 关键词输入提示
    getKeywordTips: async (data) => {
        let url = root+'/user/keyword/tip';
        console.log("get req:");
        console.log(data);
        // return {
        //     error: 0,
        //     result: [
        //         "相关关键词1号",
        //         "相关关键词2号",
        //         "相关关键词3号",
        //         "相关关键词4号",
        //         "相关关键词5号"
        //     ]
        // }
        return await axios_get(url, data);
    },
    // 获取爬虫任务列表
    getTaskList: async (data) => {
        let url = root+'/user/spider/tasklist';
        console.log("get req:");
        console.log(data);
        // return {
        //     error: 0,
        //     total: 25,
        //     result: [
        //         { id: 1, title: "测试论文标题2他比较长测试论文标题1" },
        //         { id: 2, title: "测试论文标题2他比较长测试论文标题2" },
        //         { id: 3, title: "测试论文标题2他比较长测试论文标题3" },
        //         { id: 4, title: "测试论文标题2他比较长测试论文标题4" },
        //         { id: 5, title: "测试论文标题2他比较长测试论文标题5" },
        //         { id: 6, title: "测试论文标题2他比较长测试论文标题6" },
        //         { id: 7, title: "测试论文标题2他比较长测试论文标题7" },
        //         { id: 8, title: "测试论文标题2他比较长测试论文标题8" },
        //         { id: 9, title: "测试论文标题2他比较长测试论文标题9" },
        //         { id: 10, title: "测试论文标题2他比较长测试论文标题10" },
        //     ]
        // }
        return await axios_get(url, data);
    },
    // 添加爬虫任务
    addTask: async (data) => {
        let url = root+'/user/task/addone';
        console.log("get req:");
        console.log(data);
        // return {
        //     error: 0,
        //     result: [
        //         { title: "测试论文标题2他比较长测试论文标题1" },
        //         { title: "测试论文标题2他比较长测试论文标题2" },
        //         { title: "测试论文标题2他比较长测试论文标题3" },
        //         { title: "测试论文标题2他比较长测试论文标题4" },
        //         { title: "测试论文标题2他比较长测试论文标题5" },
        //     ]
        // }
        return await axios_post(url, data);
    },
    // 导入爬虫任务
    importTask: async (data) => {
        let url = root+'/user/task/addall';
        console.log("get req:");
        console.log(data);
        // return {
        //     error: 0
        // }
        return await axios_post(url, data);
    },
    // 删除爬虫任务
    deleteTask: async (data) => {
        let url = root+'/user/task/delete';
        console.log("get req:");
        console.log(data);
        // return {
        //     error: 0
        // }
        return await axios_post(url, data);
    },
    // 删除论文记录
    deletePage: async (data) => {
        let url = root+'/user/title/delete';
        console.log("get req:");
        console.log(data);
        // return {
        //     error: 0
        // }
        return await axios_post(url, data);
    },
    // 执行爬虫
    runCrawl: async (data) => {
        let url = root+'/user/spider';
        console.log("get req:");
        console.log(data);
        // return {
        //     error: 0
        // }
        return await axios_post(url, data);
    },
    // 获取热词
    getHotwords: async (data) => {
        let url = root+'/user/hotword/show';
        console.log("get req:");
        console.log(data);
        // return {
        //     error: 0,
        //     result: [
        //         { x: "关键词1", value: "107"},
        //         { x: "关键词2", value: "90"},
        //         { x: "关键词3", value: "88"},
        //         { x: "关键词4", value: "85"},
        //         { x: "关键词5", value: "79"},
        //         { x: "关键词6", value: "77"},
        //         { x: "关键词7", value: "50"},
        //         { x: "关键词8", value: "51"},
        //         { x: "关键词9", value: "44"},
        //         { x: "关键词10", value: "33"},
        //         { x: "关键词11", value: "30"},
        //         { x: "关键词12", value: "27"},
        //         { x: "关键词13", value: "20"},
        //         { x: "关键词14", value: "15"},
        //         { x: "关键词15", value: "1"},
        //     ]
        // }
        return await axios_get(url, data);
    },
    // 获取关键词排行
    getRank: async (data) => {
        let url = root+'/user/hotword/caculate';
        console.log("get req:");
        console.log(data);
        let res =  await axios_get(url, data);
        let newRes = [];
        for(let i=0; i<res.result.length; i++) {
            let inner = {
                index: i+(data.page-1)*10+1,
                keyword: res.result[i].keyword,
                pages: res.result[i].pages,
                riserate: res.result[i].riserate,
            };
            newRes.push(inner);
        }
        return {
            total: res.total,
            result: newRes
        };
    },
    // 热词折线图数据获取
    getHotwordLine: async (data) => {
        let url = root+'/user/hotword/search';
        console.log("get req:");
        console.log(data);
        // return {
        //     error: 0,
        //     result: [
        //         {
        //             date: "2020-08",
        //             CVPR: 99,
        //             ICCV: 89,
        //             ECCV: 88
        //         },
        //         {
        //             date: "2020-09",
        //             CVPR: 108,
        //             ICCV: 88,
        //             ECCV: 99
        //         },
        //         {
        //             date: "2020-10",
        //             CVPR: 100,
        //             ICCV: 88,
        //             ECCV: 95
        //         },
        //         {
        //             date: "2020-11",
        //             CVPR: 101,
        //             ICCV: 89,
        //             ECCV: 100
        //         },
        //         {
        //             date: "2020-12",
        //             CVPR: 100,
        //             ICCV: 87,
        //             ECCV: 88
        //         }
        //     ]
        // }
        return await axios_get(url, data);
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
            //reject(res.data)
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
                //transform data from text to form
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
            //reject(res.data)
        })
        .catch(function(err){
            reject(err);
        })
    })
}

export default request;