
-- Inserir agricultores fictícios
INSERT INTO agricultores (nome, telefone, cidade)
VALUES
    ('Carlos Ferreira', '11955554444', 'Ribeirao Preto'),
    ('Ana Souza', '11944443333', 'Sorocaba');

-- Inserir uma plantação para Carlos Ferreira
INSERT INTO plantacoes (
    agricultor_id,
    cultura,
    area_hectares,
    data_plantio
)
SELECT
    id,
    'Soja',
    12.50,
    '2026-10-01'
FROM agricultores
WHERE nome = 'Carlos Ferreira'
  AND telefone = '11955554444';

-- Inserir uma colheita para a plantação de Carlos
INSERT INTO colheitas (
    plantacao_id,
    quantidade_kg,
    data_colheita
)
SELECT
    p.id,
    3500.00,
    '2027-02-15'
FROM plantacoes p
JOIN agricultores a ON a.id = p.agricultor_id
WHERE a.nome = 'Carlos Ferreira'
  AND a.telefone = '11955554444'
  AND p.cultura = 'Soja';
