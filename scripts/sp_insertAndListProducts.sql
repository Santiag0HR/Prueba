CREATE OR REPLACE PROCEDURE sp_insertAndListProducts(
    p_id_producto IN NUMBER,
    p_nombre IN VARCHAR2,
    p_fecha_registro IN DATE,
    p_cursor OUT SYS_REFCURSOR,
    p_codigo_respuesta OUT NUMBER,
    p_mensaje_respuesta OUT VARCHAR2
) AS
BEGIN
    -- Insertar el producto
INSERT INTO productos (id_producto, nombre, fecha_registro)
VALUES (p_id_producto, p_nombre, p_fecha_registro);

-- Consultar lista de productos
OPEN p_cursor FOR
SELECT id_producto, nombre, fecha_registro
FROM productos order by fecha_registro desc;

-- Respuestas
p_codigo_respuesta := 0;
    p_mensaje_respuesta := 'Ejecucion con exito';
commit;
EXCEPTION
    WHEN OTHERS THEN
        -- En caso de error, hacer rollback
        ROLLBACK;
        p_codigo_respuesta := 1;
        p_mensaje_respuesta := 'Error: ' || SQLCODE || ' - ' || SQLERRM;
END;