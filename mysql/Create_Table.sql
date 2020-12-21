-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema MOVIES
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema MOVIES
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `MOVIES` DEFAULT CHARACTER SET utf8 ;
USE `MOVIES` ;

-- -----------------------------------------------------
-- Table `MOVIES`.`ACTOR`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `MOVIES`.`ACTOR` (
  `actor_code` CHAR(9) NOT NULL,
  `fname` VARCHAR(45) NOT NULL,
  `lname` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`actor_code`),
  UNIQUE INDEX `actor_code_UNIQUE` (`actor_code` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `MOVIES`.`DIRECTOR`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `MOVIES`.`DIRECTOR` (
  `Director_Code` CHAR(9) NOT NULL,
  `fname` VARCHAR(45) NOT NULL,
  `lname` VARCHAR(45) NOT NULL,
  `actor_code` CHAR(9) NULL,
  PRIMARY KEY (`Director_Code`),
  UNIQUE INDEX `Director_Code_UNIQUE` (`Director_Code` ASC) VISIBLE,
  INDEX `actor` (`actor_code` ASC) VISIBLE,
  CONSTRAINT `fk_DIRECTOR_ACTOR1`
    FOREIGN KEY (`actor_code`)
    REFERENCES `MOVIES`.`ACTOR` (`actor_code`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `MOVIES`.`MOVIE`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `MOVIES`.`MOVIE` (
  `MOVIE_CODE` CHAR(9) NOT NULL,
  `title` VARCHAR(45) NOT NULL,
  `Director_Code` CHAR(9) NOT NULL,
  PRIMARY KEY (`MOVIE_CODE`),
  UNIQUE INDEX `MOVIE_CODE_UNIQUE` (`MOVIE_CODE` ASC) VISIBLE,
  INDEX `director` (`Director_Code` ASC) VISIBLE,
  CONSTRAINT `fk_MOVIE_DIRECTOR1`
    FOREIGN KEY (`Director_Code`)
    REFERENCES `MOVIES`.`DIRECTOR` (`Director_Code`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `MOVIES`.`PERFORMS_IN`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `MOVIES`.`PERFORMS_IN` (
  `movie_code` CHAR(9) NOT NULL,
  `actor_code` CHAR(9) NOT NULL,
  PRIMARY KEY (`movie_code`, `actor_code`),
  INDEX `actor` (`actor_code` ASC) VISIBLE,
  INDEX `movie` (`movie_code` ASC) VISIBLE,
  CONSTRAINT `fk_MOVIE_has_ACTOR_MOVIE`
    FOREIGN KEY (`movie_code`)
    REFERENCES `MOVIES`.`MOVIE` (`MOVIE_CODE`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_MOVIE_has_ACTOR_ACTOR1`
    FOREIGN KEY (`actor_code`)
    REFERENCES `MOVIES`.`ACTOR` (`actor_code`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `MOVIES`.`PRODUCER`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `MOVIES`.`PRODUCER` (
  `producer_code` CHAR(9) NOT NULL,
  `fname` VARCHAR(45) NOT NULL,
  `lname` VARCHAR(45) NOT NULL,
  `actor_code` CHAR(9) NULL,
  PRIMARY KEY (`producer_code`),
  UNIQUE INDEX `producer_code_UNIQUE` (`producer_code` ASC) VISIBLE,
  INDEX `actor` (`actor_code` ASC) VISIBLE,
  CONSTRAINT `fk_PRODUCER_ACTOR1`
    FOREIGN KEY (`actor_code`)
    REFERENCES `MOVIES`.`ACTOR` (`actor_code`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `MOVIES`.`LEAD_ROLE`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `MOVIES`.`LEAD_ROLE` (
  `MOVIE_CODE` CHAR(9) NOT NULL,
  `actor_code` CHAR(9) NOT NULL,
  PRIMARY KEY (`MOVIE_CODE`, `actor_code`),
  INDEX `actor` (`actor_code` ASC) VISIBLE,
  INDEX `movie` (`MOVIE_CODE` ASC) VISIBLE,
  CONSTRAINT `fk_MOVIE_has_ACTOR_MOVIE1`
    FOREIGN KEY (`MOVIE_CODE`)
    REFERENCES `MOVIES`.`MOVIE` (`MOVIE_CODE`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_MOVIE_has_ACTOR_ACTOR2`
    FOREIGN KEY (`actor_code`)
    REFERENCES `MOVIES`.`ACTOR` (`actor_code`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `MOVIES`.`PRODUCES`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `MOVIES`.`PRODUCES` (
  `MOVIE_CODE` CHAR(9) NOT NULL,
  `producer_code` CHAR(9) NOT NULL,
  PRIMARY KEY (`MOVIE_CODE`, `producer_code`),
  INDEX `producer` (`producer_code` ASC) VISIBLE,
  INDEX `movie` (`MOVIE_CODE` ASC) VISIBLE,
  CONSTRAINT `fk_MOVIE_has_PRODUCER_MOVIE1`
    FOREIGN KEY (`MOVIE_CODE`)
    REFERENCES `MOVIES`.`MOVIE` (`MOVIE_CODE`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_MOVIE_has_PRODUCER_PRODUCER1`
    FOREIGN KEY (`producer_code`)
    REFERENCES `MOVIES`.`PRODUCER` (`producer_code`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
