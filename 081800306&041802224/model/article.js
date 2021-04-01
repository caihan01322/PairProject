/* eslint-disable */
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
            if (i >= list.length) {
                break
            }
            ids.push(list[i].arcid);
        }
    }

    static async getArticlesByContent(key, page) {
        let arr = await Article.findAll({
            where: {
                conclude: {
                    [Op.substring]: key,
                }
            }
        })
        let articles = []
        for (let i = page * 5; i < page * 5 + 5; i++) {
            if (i >= arr.length) {
                break
            }
            articles.push(arr[i]);
        }
        return articles
    }

    static async getArticlesByTitle(key, page) {
        let arr = await Article.findAll({
            where: {
                title: {
                    [Op.substring]: key,
                }
            }
        })
        let articles = []
        for (let i = page * 5; i < page * 5 + 5; i++) {
            if (i >= arr.length) {
                break
            }
            articles.push(arr[i]);
        }
        return articles
    }
    static async getArticleByid(id) {
        let article = await Article.findOne({
            where: {
                id,
            },
        });
        return article;
    }

    static async getIdsByKey(key) {
        let ids = await keywords.getIdByKey(key);
        console.log(ids);
        return ids;
    }

    static async updateArticle({ id, title, conclude, link, magazine }) { // eslint-disable-line
        await Article.update({ title, conclude, link, magazine }, { // eslint-disable-line
            where: { id },
        });
    }

    static async deleteArticle({ id }) {
        await Article.destroy({
            where: {
                id,
            },
        });
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