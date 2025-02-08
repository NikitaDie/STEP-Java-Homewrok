
--delete
CREATE OR REPLACE FUNCTION record_deleted_order()
RETURNS TRIGGER AS $$
BEGIN
    INSERT INTO deleted_entities (entity_name, entity_id, deleted_by, deleted_at, reason)
    VALUES ('orders', OLD.id, CURRENT_USER, NEW.deleted_at, 'Soft delete');
    RETURN OLD;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE  TRIGGER orders_soft_delete_trigger
    AFTER UPDATE ON orders
    FOR EACH ROW
    WHEN (OLD.deleted_at IS NULL AND NEW.deleted_at IS NOT NULL)
    EXECUTE FUNCTION record_deleted_order();

--update
CREATE OR REPLACE FUNCTION record_updated_order()
RETURNS TRIGGER AS $$
BEGIN
    INSERT INTO updated_entities (entity_name, entity_id, updated_by, updated_at, changes)
    VALUES ('orders', OLD.id, CURRENT_USER, NOW(),
            jsonb_strip_nulls(
                    jsonb_build_object(
                            'total_price',
                            CASE WHEN OLD.total_price IS DISTINCT FROM NEW.total_price
                                THEN jsonb_build_object('old', OLD.total_price, 'new', NEW.total_price)
                                ELSE NULL END,
                            'discount',
                            CASE WHEN OLD.discount IS DISTINCT FROM NEW.discount
                                THEN jsonb_build_object('old', OLD.discount, 'new', NEW.discount)
                                ELSE NULL END
                    )
            )
           );
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE TRIGGER orders_update_trigger
    AFTER UPDATE ON orders
    FOR EACH ROW
    EXECUTE FUNCTION record_updated_order();
