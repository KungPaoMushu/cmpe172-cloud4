/* jshint indent: 2 */

module.exports = function(sequelize, DataTypes) {
  return sequelize.define('language', {
    language_id: {
      type: DataTypes.INTEGER(3).UNSIGNED,
      allowNull: false,
      primaryKey: true,
      autoIncrement: true
    },
    name: {
      type: DataTypes.CHAR(20),
      allowNull: false
    },
    last_update: {
      type: DataTypes.DATE,
      allowNull: false,
      defaultValue: sequelize.fn('current_timestamp')
    }
  }, {
    tableName: 'language',
    timestamps: false
  });
};
