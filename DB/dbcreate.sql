-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema Periodicals_system
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `Periodicals_system` ;

-- -----------------------------------------------------
-- Schema Periodicals_system
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `Periodicals_system` DEFAULT CHARACTER SET utf8 ;
USE `Periodicals_system` ;

-- -----------------------------------------------------
-- Table `Periodicals_system`.`user_authorization`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Periodicals_system`.`user_authorization` ;

CREATE TABLE IF NOT EXISTS `Periodicals_system`.`user_authorization` (
  `email` VARCHAR(90) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `role` ENUM('admin', 'user', 'unknown') NOT NULL DEFAULT 'unknown',
  `status` ENUM('active', 'banned') NOT NULL DEFAULT 'active',
  `balance` DECIMAL(9,2) NULL DEFAULT 0.00,
  PRIMARY KEY (`email`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `Periodicals_system`.`user_details`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Periodicals_system`.`user_details` ;

CREATE TABLE IF NOT EXISTS `Periodicals_system`.`user_details` (
  `user_authorization_email` VARCHAR(90) NOT NULL,
  `first_name` VARCHAR(45) NULL,
  `last_name` VARCHAR(45) NULL,
  `delivery_address` VARCHAR(300) NULL,
  `telephone` VARCHAR(13) NULL,
  PRIMARY KEY (`user_authorization_email`),
  CONSTRAINT `fk_user_details_user_authorization`
    FOREIGN KEY (`user_authorization_email`)
    REFERENCES `Periodicals_system`.`user_authorization` (`email`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `Periodicals_system`.`topic`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Periodicals_system`.`topic` ;

CREATE TABLE IF NOT EXISTS `Periodicals_system`.`topic` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(150) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `Periodicals_system`.`publication`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Periodicals_system`.`publication` ;

CREATE TABLE IF NOT EXISTS `Periodicals_system`.`publication` (
  `index` VARCHAR(10) NOT NULL,
  `name` VARCHAR(150) NOT NULL,
  `description` VARCHAR(400) NOT NULL,
  `language` VARCHAR(45) NOT NULL,
  `price` DECIMAL(12,2) NOT NULL,
  `img` VARCHAR(100) NULL,
  `topic_id` INT NOT NULL,
  PRIMARY KEY (`index`, `topic_id`),
  UNIQUE INDEX `index_UNIQUE` (`index` ASC) VISIBLE,
  INDEX `fk_publication_topic1_idx` (`topic_id` ASC) VISIBLE,
  CONSTRAINT `fk_publication_topic1`
    FOREIGN KEY (`topic_id`)
    REFERENCES `Periodicals_system`.`topic` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `Periodicals_system`.`payment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Periodicals_system`.`payment` ;

CREATE TABLE IF NOT EXISTS `Periodicals_system`.`payment` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `user_authorization_email` VARCHAR(90) NOT NULL,
  `date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `total_price` DECIMAL(9,2) NOT NULL,
  PRIMARY KEY (`id`, `user_authorization_email`),
  INDEX `fk_payment_user_authorization1_idx` (`user_authorization_email` ASC) VISIBLE,
  CONSTRAINT `fk_payment_user_authorization1`
    FOREIGN KEY (`user_authorization_email`)
    REFERENCES `Periodicals_system`.`user_authorization` (`email`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `Periodicals_system`.`subscription`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Periodicals_system`.`subscription` ;

CREATE TABLE IF NOT EXISTS `Periodicals_system`.`subscription` (
  `user_authorization_email` VARCHAR(90) NOT NULL,
  `publication_index` VARCHAR(10) NOT NULL,
  `payment_id` BIGINT NOT NULL,
  `status` ENUM('active', 'stopped') NOT NULL DEFAULT 'stopped',
  `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `expired_at` TIMESTAMP NULL,
  PRIMARY KEY (`user_authorization_email`, `publication_index`, `payment_id`),
  INDEX `fk_user_authorization_has_publication_publication1_idx` (`publication_index` ASC) VISIBLE,
  INDEX `fk_user_authorization_has_publication_user_authorization1_idx` (`user_authorization_email` ASC) VISIBLE,
  INDEX `fk_subscription_payment1_idx` (`payment_id` ASC) VISIBLE,
  CONSTRAINT `fk_user_authorization_has_publication_user_authorization1`
    FOREIGN KEY (`user_authorization_email`)
    REFERENCES `Periodicals_system`.`user_authorization` (`email`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_authorization_has_publication_publication1`
    FOREIGN KEY (`publication_index`)
    REFERENCES `Periodicals_system`.`publication` (`index`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_subscription_payment1`
    FOREIGN KEY (`payment_id`)
    REFERENCES `Periodicals_system`.`payment` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
