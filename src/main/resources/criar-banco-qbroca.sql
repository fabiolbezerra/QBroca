-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema qbroca
-- -----------------------------------------------------
-- banco de dados de restaurantes
DROP SCHEMA IF EXISTS `qbroca` ;

-- -----------------------------------------------------
-- Schema qbroca
--
-- banco de dados de restaurantes
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `qbroca` DEFAULT CHARACTER SET utf8 ;
USE `qbroca` ;

-- -----------------------------------------------------
-- Table `qbroca`.`restaurante`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `qbroca`.`restaurante` ;

CREATE TABLE IF NOT EXISTS `qbroca`.`restaurante` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `qbroca`.`cliente`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `qbroca`.`cliente` ;

CREATE TABLE IF NOT EXISTS `qbroca`.`cliente` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `sobrenome` VARCHAR(45) NULL,
  `telefone` VARCHAR(11) NOT NULL,
  `data_hora` DATETIME NULL,
  `restaurante` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_cliente_restaurante_idx` (`restaurante` ASC),
  CONSTRAINT `fk_cliente_restaurante`
    FOREIGN KEY (`restaurante`)
    REFERENCES `qbroca`.`restaurante` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `qbroca`.`endereco`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `qbroca`.`endereco` ;

CREATE TABLE IF NOT EXISTS `qbroca`.`endereco` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `logradouro` VARCHAR(50) NOT NULL,
  `numero` VARCHAR(15) NULL,
  `complemento` VARCHAR(50) NULL,
  `cep` VARCHAR(10) NULL,
  `bairro` VARCHAR(50) NULL,
  `cidade` VARCHAR(50) NULL,
  `estado` VARCHAR(2) NULL,
  `cliente` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_endereco_cliente1_idx` (`cliente` ASC),
  CONSTRAINT `fk_endereco_cliente1`
    FOREIGN KEY (`cliente`)
    REFERENCES `qbroca`.`cliente` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `qbroca`.`categoria`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `qbroca`.`categoria` ;

CREATE TABLE IF NOT EXISTS `qbroca`.`categoria` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(30) NOT NULL,
  `restaurante` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_categoria_restaurante1_idx` (`restaurante` ASC),
  CONSTRAINT `fk_categoria_restaurante1`
    FOREIGN KEY (`restaurante`)
    REFERENCES `qbroca`.`restaurante` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `qbroca`.`produto`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `qbroca`.`produto` ;

CREATE TABLE IF NOT EXISTS `qbroca`.`produto` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(30) NOT NULL,
  `preco` DECIMAL(6,2) NOT NULL,
  `descricao` VARCHAR(150) NULL,
  `categoria` INT NOT NULL,
  `restaurante` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_produto_categoria1_idx` (`categoria` ASC),
  INDEX `fk_produto_restaurante1_idx` (`restaurante` ASC),
  CONSTRAINT `fk_produto_categoria1`
    FOREIGN KEY (`categoria`)
    REFERENCES `qbroca`.`categoria` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_produto_restaurante1`
    FOREIGN KEY (`restaurante`)
    REFERENCES `qbroca`.`restaurante` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `qbroca`.`pedido`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `qbroca`.`pedido` ;

CREATE TABLE IF NOT EXISTS `qbroca`.`pedido` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `data_hora` DATETIME NOT NULL,
  `total` DECIMAL(6,2) NULL,
  `cliente` INT NOT NULL,
  `restaurante` INT NOT NULL,
  `endereco` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_pedido_cliente1_idx` (`cliente` ASC),
  INDEX `fk_pedido_restaurante1_idx` (`restaurante` ASC),
  INDEX `fk_pedido_endereco1_idx` (`endereco` ASC),
  CONSTRAINT `fk_pedido_cliente1`
    FOREIGN KEY (`cliente`)
    REFERENCES `qbroca`.`cliente` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_pedido_restaurante1`
    FOREIGN KEY (`restaurante`)
    REFERENCES `qbroca`.`restaurante` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_pedido_endereco1`
    FOREIGN KEY (`endereco`)
    REFERENCES `qbroca`.`endereco` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `qbroca`.`item_pedido`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `qbroca`.`item_pedido` ;

CREATE TABLE IF NOT EXISTS `qbroca`.`item_pedido` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `quantidade` DOUBLE NOT NULL,
  `total` DECIMAL(6,2) NOT NULL,
  `observacao` VARCHAR(50) NULL,
  `produto` INT NOT NULL,
  `pedido` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_item_pedido_produto1_idx` (`produto` ASC),
  INDEX `fk_item_pedido_pedido1_idx` (`pedido` ASC),
  CONSTRAINT `fk_item_pedido_produto1`
    FOREIGN KEY (`produto`)
    REFERENCES `qbroca`.`produto` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_item_pedido_pedido1`
    FOREIGN KEY (`pedido`)
    REFERENCES `qbroca`.`pedido` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
