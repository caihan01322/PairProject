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
