const {
    Model,
    DataTypes,
    Op,
} = require('sequelize');
const { sequelize } = require('../core/db');
const { keywords } = require('./keywords');

class Article extends Model {
    static async getArticles(list, page) {
        let ids = [];
        for (let i = page * 5; i < page * 5 + 5; i++) {
            ids.push(list[i].arcid);
        }
        return Article.findAll({
            where: {
                id: {
                    [Op.in]: ids,
                },
            },
        });
    }

    static async getIdsByKey(key) {
        let ids = await keywords.getIdByKey(key);
        return ids;
    }
    static async getIdsCVPR() {
        let sql = `select id from article where magazine = 'CVPR' and year = 2017;`
        return await Article.findAll({
            attributes: ['id'],
            where: {
                magazine,
                year
            }
        })
    }
}
Article.init({
    id: {
        type: DataTypes.STRING,
        primaryKey: true,
    },
    title: DataTypes.STRING,
    year: DataTypes.INTEGER,
    conclude: DataTypes.STRING,
    link: DataTypes.STRING,
    magazine: DataTypes.STRING,
}, {
    sequelize,
    tableName: 'article',
});

module.exports = {
    Article,
};