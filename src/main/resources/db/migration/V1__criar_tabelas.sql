
CREATE TABLE agricultores (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    telefone VARCHAR(20),
    cidade VARCHAR(100) NOT NULL
);

CREATE TABLE plantacoes (
    id BIGSERIAL PRIMARY KEY,
    agricultor_id BIGINT NOT NULL,
    cultura VARCHAR(100) NOT NULL,
    area_hectares NUMERIC(10,2) NOT NULL,
    data_plantio DATE NOT NULL,

    CONSTRAINT fk_agricultor
        FOREIGN KEY (agricultor_id)
        REFERENCES agricultores(id),

    CONSTRAINT chk_area_positiva
        CHECK (area_hectares > 0)
);

CREATE TABLE colheitas (
    id BIGSERIAL PRIMARY KEY,
    plantacao_id BIGINT NOT NULL,
    quantidade_kg NUMERIC(12,2) NOT NULL,
    data_colheita DATE NOT NULL,

    CONSTRAINT fk_plantacao
        FOREIGN KEY (plantacao_id)
        REFERENCES plantacoes(id),

    CONSTRAINT chk_quantidade_positiva
        CHECK (quantidade_kg > 0)
);
