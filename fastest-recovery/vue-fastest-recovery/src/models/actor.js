'use strict';
module.exports = function(sequelize, DataTypes) {
  var actor =  sequelize.define('actor', {
    actor_id: {
      type: DataTypes.INTEGER(5).UNSIGNED,
      allowNull: false,
      primaryKey: true,
      autoIncrement: true,
      onUpdate: 'CASCADE',
      onDelete: 'CASCADE'
    },
    first_name: {
      type: DataTypes.STRING(45),
      allowNull: false
    },
    last_name: {
      type: DataTypes.STRING(45),
      allowNull: false
    },
    last_update: {
      type: DataTypes.DATE,
      allowNull: false,
      defaultValue: sequelize.fn('current_timestamp')
    }
  }, {
    tableName: 'actor',
    timestamps: false
  });


  actor.associate = function(models) {
    actor.hasMany(models.film_actor,{
     foreignKey: 'actor_id',
     onDelete: 'CASCADE',
     onUpdate: 'CASCADE' 
   });
  };

  return actor
};

//     tableName: 'actor'