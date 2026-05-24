CREATE TABLE tbl_entregas(
    numero_entrega BIGINT(20) NOT NULL AUTO_INCREMENT,
    numero_pedido BIGINT(20) NOT NULL,
    nome_entregador VARCHAR(100) NULL,
    status_entrega VARCHAR(20) NOT NULL,
    data_entrega DATE NULL,
    PRIMARY KEY (numero_entrega)
);