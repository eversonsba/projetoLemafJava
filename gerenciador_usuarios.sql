-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Tempo de geração: 19-Mar-2020 às 14:00
-- Versão do servidor: 10.4.11-MariaDB
-- versão do PHP: 7.4.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `gerenciador_usuarios`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `cargos`
--

CREATE TABLE `cargos` (
  `cod` int(11) NOT NULL,
  `nome` varchar(150) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `cargos`
--

INSERT INTO `cargos` (`cod`, `nome`) VALUES
(4, 'Diretor'),
(5, 'Gestor'),
(7, 'Programador');

-- --------------------------------------------------------

--
-- Estrutura da tabela `perfis`
--

CREATE TABLE `perfis` (
  `id` int(11) NOT NULL,
  `nome` varchar(150) DEFAULT NULL,
  `cpf_usuario` varchar(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `perfis`
--

INSERT INTO `perfis` (`id`, `nome`, `cpf_usuario`) VALUES
(10, 'vecinho', '09429511661');

-- --------------------------------------------------------

--
-- Estrutura da tabela `usuarios`
--

CREATE TABLE `usuarios` (
  `nome` varchar(150) NOT NULL,
  `cpf` varchar(11) NOT NULL,
  `data_nacimento` date DEFAULT NULL,
  `sexo` enum('M','F') DEFAULT NULL,
  `cod_cargo` int(11) DEFAULT NULL,
  `data_cadastro` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `usuarios`
--

INSERT INTO `usuarios` (`nome`, `cpf`, `data_nacimento`, `sexo`, `cod_cargo`, `data_cadastro`) VALUES
('Everson', '09429511661', '1997-09-16', 'M', 7, '2020-03-19');

--
-- Índices para tabelas despejadas
--

--
-- Índices para tabela `cargos`
--
ALTER TABLE `cargos`
  ADD PRIMARY KEY (`cod`);

--
-- Índices para tabela `perfis`
--
ALTER TABLE `perfis`
  ADD PRIMARY KEY (`id`),
  ADD KEY `cpf_usuario` (`cpf_usuario`);

--
-- Índices para tabela `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`cpf`),
  ADD KEY `cod_cargo` (`cod_cargo`);

--
-- AUTO_INCREMENT de tabelas despejadas
--

--
-- AUTO_INCREMENT de tabela `cargos`
--
ALTER TABLE `cargos`
  MODIFY `cod` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT de tabela `perfis`
--
ALTER TABLE `perfis`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- Restrições para despejos de tabelas
--

--
-- Limitadores para a tabela `perfis`
--
ALTER TABLE `perfis`
  ADD CONSTRAINT `perfis_ibfk_1` FOREIGN KEY (`cpf_usuario`) REFERENCES `usuarios` (`cpf`);

--
-- Limitadores para a tabela `usuarios`
--
ALTER TABLE `usuarios`
  ADD CONSTRAINT `usuarios_ibfk_1` FOREIGN KEY (`cod_cargo`) REFERENCES `cargos` (`cod`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
