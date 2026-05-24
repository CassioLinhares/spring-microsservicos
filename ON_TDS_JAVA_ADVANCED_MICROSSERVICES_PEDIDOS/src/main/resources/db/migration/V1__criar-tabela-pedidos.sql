 CREATE TABLE tbl_pedidos(
    numero_pedido BIGINT(20) NOT NULL AUTO_INCREMENT ,
    nome_cliente VARCHAR(100) NOT NULL,
    data_pedido DATE NULL,
    valor DECIMAL(9,3) NOT NULL,
    status_entrega VARCHAR(20) NOT NULL,
    PRIMARY KEY (numero_pedido)
 );