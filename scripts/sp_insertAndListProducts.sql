CREATE OR REPLACE PROCEDURE sp_insertAndListProducts(
    p_id_producto IN NUMBER,
    p_nombre IN VARCHAR2,
    p_cursor OUT SYS_REFCURSOR
) AS
BEGIN
    -- Insertar el producto
INSERT INTO productos (id_producto, nombre, fec_registro)
VALUES (p_id_producto, p_nombre, SYSDATE);

-- Consultar productos del día
OPEN p_cursor FOR
SELECT id_producto, nombre, fec_registro
FROM productos;

-- Respuesta exitosa
commit;
EXCEPTION
    WHEN DUP_VAL_ON_INDEX THEN
        RAISE_APPLICATION_ERROR(-20001, 'Error: ID de producto duplicado.');
    WHEN OTHERS THEN
        -- En caso de error, hacer rollback
        ROLLBACK;
            RAISE_APPLICATION_ERROR(-20001, 'Error al gestionar productos: ' || SQLERRM);
END;