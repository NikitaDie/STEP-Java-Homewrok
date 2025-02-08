
--delete
CREATE OR REPLACE FUNCTION record_deleted_beverage()
RETURNS TRIGGER AS $$
BEGIN
    INSERT INTO deleted_entities (entity_name, entity_id, deleted_by, deleted_at, reason)
    VALUES ('beverages', OLD.id, CURRENT_USER, NEW.deleted_at, 'Soft delete');
    RETURN OLD;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE TRIGGER beverages_soft_delete_trigger
    AFTER UPDATE ON beverages
    FOR EACH ROW
    WHEN (OLD.deleted_at IS NULL AND NEW.deleted_at IS NOT NULL)
    EXECUTE FUNCTION record_deleted_beverage();

--update
CREATE OR REPLACE FUNCTION record_updated_beverage()
RETURNS TRIGGER AS $$
BEGIN
    INSERT INTO updated_entities (entity_name, entity_id, updated_by, updated_at, changes)
    VALUES ('beverages', OLD.id, CURRENT_USER, NOW(),
            jsonb_strip_nulls(
                    jsonb_build_object(
                            'price',
                            CASE WHEN OLD.price IS DISTINCT FROM NEW.price
                                THEN jsonb_build_object('old', OLD.price, 'new', NEW.price)
                                ELSE NULL END
                    )
            )
           );
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE TRIGGER beverages_update_trigger
    AFTER UPDATE ON beverages
    FOR EACH ROW
    EXECUTE FUNCTION record_updated_beverage();
