const {
  Model,
  DataTypes,
} = require('sequelize');
const { sequelize } = require('../core/db');

class Article extends Model {

}
Article.init({
  id: DataTypes.INTEGER,
  title: DataTypes.STRING,
  year: DataTypes.INTEGER,
  conclude: DataTypes.STRING,
  link: DataTypes.STRING,
}, {
  sequelize,
  tableName: 'article',
});

module.exports = {
  Article,
};
