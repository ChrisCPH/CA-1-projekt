-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema CA-1
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `CA-1` ;

-- -----------------------------------------------------
-- Schema CA-1
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `CA-1` DEFAULT CHARACTER SET utf8 ;
-- -----------------------------------------------------
-- Schema test
-- -----------------------------------------------------
USE `CA-1` ;

-- -----------------------------------------------------
-- Table `CA-1`.`city_info`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `CA-1`.`city_info` ;

CREATE TABLE IF NOT EXISTS `CA-1`.`city_info` (
  `cityInfo_id` INT NOT NULL AUTO_INCREMENT,
  `zipcode` VARCHAR(45) NOT NULL,
  `city` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`cityInfo_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `CA-1`.`address`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `CA-1`.`address` ;

CREATE TABLE IF NOT EXISTS `CA-1`.`address` (
  `address_id` INT NOT NULL AUTO_INCREMENT,
  `street` VARCHAR(45) NOT NULL,
  `additional_info` VARCHAR(45) NOT NULL,
  `city_info_id` INT NOT NULL,
  PRIMARY KEY (`address_id`),
  INDEX `fk_address_city_info1_idx` (`city_info_id` ASC) VISIBLE,
  CONSTRAINT `fk_address_city_info1`
    FOREIGN KEY (`city_info_id`)
    REFERENCES `CA-1`.`city_info` (`cityInfo_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `CA-1`.`person`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `CA-1`.`person` ;

CREATE TABLE IF NOT EXISTS `CA-1`.`person` (
  `person_id` INT NOT NULL AUTO_INCREMENT,
  `firstName` VARCHAR(45) NOT NULL,
  `lastName` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `address_id` INT NOT NULL,
  PRIMARY KEY (`person_id`),
  INDEX `fk_person_address1_idx` (`address_id` ASC) VISIBLE,
  CONSTRAINT `fk_person_address1`
    FOREIGN KEY (`address_id`)
    REFERENCES `CA-1`.`address` (`address_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `CA-1`.`hobby`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `CA-1`.`hobby` ;

CREATE TABLE IF NOT EXISTS `CA-1`.`hobby` (
  `hobby_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `description` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`hobby_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `CA-1`.`person_hobby`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `CA-1`.`person_hobby` ;

CREATE TABLE IF NOT EXISTS `CA-1`.`person_hobby` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `person_id` INT NOT NULL,
  `hobby_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_PersonHobby_Person1_idx` (`person_id` ASC) VISIBLE,
  INDEX `fk_PersonHobby_Hobby1_idx` (`hobby_id` ASC) VISIBLE,
  CONSTRAINT `fk_PersonHobby_Person1`
    FOREIGN KEY (`person_id`)
    REFERENCES `CA-1`.`person` (`person_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_PersonHobby_Hobby1`
    FOREIGN KEY (`hobby_id`)
    REFERENCES `CA-1`.`hobby` (`hobby_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `CA-1`.`phone`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `CA-1`.`phone` ;

CREATE TABLE IF NOT EXISTS `CA-1`.`phone` (
  `phone_id` INT NOT NULL AUTO_INCREMENT,
  `number` VARCHAR(45) NOT NULL,
  `description` VARCHAR(45) NOT NULL,
  `person_id` INT NOT NULL,
  PRIMARY KEY (`phone_id`),
  INDEX `fk_phone_person1_idx` (`person_id` ASC) VISIBLE,
  CONSTRAINT `fk_phone_person1`
    FOREIGN KEY (`person_id`)
    REFERENCES `CA-1`.`person` (`person_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
