const {
  Model,
  DataTypes,
} = require('sequelize');
const { sequelize } = require('../core/db');

class keywords extends Model {

}
keywords.init({
  id: DataTypes.INTEGER,
  keywords: DataTypes.STRING,
}, {
  sequelize,
  tableName: 'keywords',
});

module.exports = {
  keywords,
};
