/* jshint indent: 2 */

module.exports = function(sequelize, DataTypes) {
  var Store = sequelize.define('Store', {
    store_id: {
      type: DataTypes.INTEGER(3).UNSIGNED,
      allowNull: false,
      primaryKey: true,
      autoIncrement: true
    },
//    manager_staff_id: {
//      type: DataTypes.INTEGER(3).UNSIGNED,
//      allowNull: false,
//      references: {
//        model: 'staff',
//        key: 'staff_id'
//      },
//      unique: true
//    },
    address_id: {
      type: DataTypes.INTEGER(5).UNSIGNED,
      allowNull: false,
      references: {
        model: 'address',
        key: 'address_id'
      }
    },
    last_update: {
      type: DataTypes.DATE,
      allowNull: false,
      defaultValue: sequelize.fn('current_timestamp')
    }
  }, {
    tableName: 'store',
    timestamps: false
  });
//  Store.associate = function(models) {
//    Store.hasOne(models.Staff,{ 
//      foreignKey: 'manager_staff_id',
//      constraints: false, 
//    });
//  };
  return Store
};
