-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema adv_db
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema adv_db
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `adv_db` DEFAULT CHARACTER SET utf8 ;
USE `adv_db` ;

-- -----------------------------------------------------
-- Table `adv_db`.`datasource`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `adv_db`.`datasource` (
    `ds_id` BIGINT(20) NOT NULL,
    `ds_name` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`ds_id`))
    ENGINE = InnoDB;

CREATE UNIQUE INDEX `ds_name_UNIQUE` ON `adv_db`.`datasource` (`ds_name` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `adv_db`.`campaign`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `adv_db`.`campaign` (
    `cpg_id` BIGINT(20) NOT NULL,
    `cpg_name` VARCHAR(255) NOT NULL,
    `cpg_ds_id` BIGINT(20) NOT NULL,
    PRIMARY KEY (`cpg_id`),
    CONSTRAINT `fk_campaign_datasource`
    FOREIGN KEY (`cpg_ds_id`)
    REFERENCES `adv_db`.`datasource` (`ds_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE UNIQUE INDEX `c_name_UNIQUE` ON `adv_db`.`campaign` (`cpg_ds_id` ASC, `cpg_name` ASC) VISIBLE;

CREATE INDEX `fk_campaign_datasource_idx` ON `adv_db`.`campaign` (`cpg_ds_id` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `adv_db`.`metrics`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `adv_db`.`metrics` (
    `met_id` BIGINT(20) NOT NULL,
    `met_clicks` INT NOT NULL,
    `met_impressions` INT NOT NULL,
    `met_daily` DATE NOT NULL,
    `met_cpg_id` BIGINT(20) NOT NULL,
    PRIMARY KEY (`met_id`),
    CONSTRAINT `fk_metrics_campaign1`
    FOREIGN KEY (`met_cpg_id`)
    REFERENCES `adv_db`.`campaign` (`cpg_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE INDEX `fk_metrics_campaign1_idx` ON `adv_db`.`metrics` (`met_cpg_id` ASC) VISIBLE;

CREATE SEQUENCE IF NOT EXISTS `cpg_seq`;
CREATE SEQUENCE IF NOT EXISTS `ds_seq`;
CREATE SEQUENCE IF NOT EXISTS `met_seq`;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
