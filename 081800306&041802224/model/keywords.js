const {
    Model,
    DataTypes,
    Op,
} = require('sequelize');
const { sequelize } = require('../core/db');

class keywords extends Model {
    static async getIdByKey(key) {
        let res = await keywords.findAll({
            attributes: ['arcid'],
            where: {
                keyword: {
                    [Op.eq]: key,
                },
            },
        });
        return res;
    }
    static async getTopKeys() {
        let sql = `select keyword as name,COUNT(id) as value from keywords group by keyword order by COUNT(id) DESC LIMIT 10;`
        let arr = await sequelize.query(sql)
        arr = arr[0]
        let res = [],
            obj = {}
        for (const key in arr) {
            obj = {...arr[key] }
            res.push(obj)
        }
        return res;
    }
    static async getcloud() {
        let sql = `select keyword as name,COUNT(id) as value from keywords group by keyword order by COUNT(id) DESC LIMIT 30;`
        let arr = await sequelize.query(sql)
        arr = arr[0]
        let res = [],
            obj = {}
        for (const key in arr) {
            obj = {...arr[key] }
            res.push(obj)
        }
        return res;
    }
    static async getCVPR(year) {
        let sql = `select keyword as name, COUNT(id) as value from keywords where arcid in (select id as arcid from article where magazine = 'CVPR'and year = ${year}) group by keyword order by COUNT(id) DESC LIMIT 6;`
        let arr = await sequelize.query(sql)
        arr = arr[0]
        let res = [],
            obj = {}
        for (const key in arr) {
            obj = {...arr[key] }
            res.push(obj)
        }
        return res

    }
    static async getICCV(year) {
        let sql = `select keyword as name, COUNT(id) as value from keywords where arcid in (select id as arcid from article where magazine = 'ICCV'and year = ${year}) group by keyword order by COUNT(id) DESC LIMIT 6;`
        let arr = await sequelize.query(sql)
        arr = arr[0]
        let res = [],
            obj = {}
        for (const key in arr) {
            obj = {...arr[key] }
            res.push(obj)
        }
        return res

    }
    static async getECCV(year) {
        let sql = `select keyword as name, COUNT(id) as value from keywords where arcid in (select id as arcid from article where magazine = 'ECCV'and year = ${year}) group by keyword order by COUNT(id) DESC LIMIT 6;`
        let arr = await sequelize.query(sql)
        arr = arr[0]
        let res = [],
            obj = {}
        for (const key in arr) {
            obj = {...arr[key] }
            res.push(obj)
        }
        return res

    }
}
keywords.init({
    id: {
        type: DataTypes.INTEGER,
        primaryKey: true,
        autoIncrement: true,
    },
    arcid: DataTypes.INTEGER,
    keyword: DataTypes.STRING,
}, {
    sequelize,
    tableName: 'keywords',
});

module.exports = {
    keywords,
};