/* jshint indent: 2 */

module.exports = function(sequelize, DataTypes) {
  var actor_info =  sequelize.define('actor_info', {
    actor_id: {
      type: DataTypes.INTEGER(5).UNSIGNED,
      allowNull: false,
      defaultValue: '0'
    },
    first_name: {
      type: DataTypes.STRING(45),
      allowNull: false
    },
    last_name: {
      type: DataTypes.STRING(45),
      allowNull: false
    },
    film_info: {
      type: DataTypes.TEXT,
      allowNull: true
    }
  }, {
    tableName: 'actor_info',
    timestamps: false
  });

/*
  actor_info.associate = function(models) {
    actor_info.belongsTo(models.actor,{
     foreignKey: 'actor_id',
     onDelete: 'CASCADE',
     onUpdate: 'CASCADE'
   });
  };
*/

  return actor_info;
};
