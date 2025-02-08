
--delete
CREATE OR REPLACE FUNCTION record_deleted_client()
RETURNS TRIGGER AS $$
    BEGIN
    INSERT INTO deleted_entities (entity_name, entity_id, deleted_by, deleted_at, reason)
    VALUES ('clients', OLD.id, CURRENT_USER, NEW.deleted_at, 'Soft delete');
    RETURN OLD;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE TRIGGER clients_soft_delete_trigger
    AFTER UPDATE ON clients
    FOR EACH ROW
    WHEN (OLD.deleted_at IS NULL AND NEW.deleted_at IS NOT NULL)
    EXECUTE FUNCTION record_deleted_client();

--update
CREATE OR REPLACE FUNCTION record_updated_client()
RETURNS TRIGGER AS $$
BEGIN
    INSERT INTO updated_entities (entity_name, entity_id, updated_by, updated_at, changes)
    VALUES ('clients', OLD.id, CURRENT_USER, NOW(),
            jsonb_strip_nulls(
                    jsonb_build_object(
                            'firstname',
                            CASE WHEN OLD.firstname IS DISTINCT FROM NEW.firstname
                                THEN jsonb_build_object('old', OLD.firstname, 'new', NEW.firstname)
                                ELSE NULL END,
                            'surname',
                            CASE WHEN OLD.surname IS DISTINCT FROM NEW.surname
                                THEN jsonb_build_object('old', OLD.surname, 'new', NEW.surname)
                                ELSE NULL END,
                            'middle_name',
                            CASE WHEN OLD.middle_name IS DISTINCT FROM NEW.middle_name
                                THEN jsonb_build_object('old', OLD.middle_name, 'new', NEW.middle_name)
                                ELSE NULL END,
                            'phone',
                            CASE WHEN OLD.phone IS DISTINCT FROM NEW.phone
                                THEN jsonb_build_object('old', OLD.phone, 'new', NEW.phone)
                                ELSE NULL END,
                            'address',
                            CASE WHEN OLD.address IS DISTINCT FROM NEW.address
                                THEN jsonb_build_object('old', OLD.address, 'new', NEW.address)
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

CREATE OR REPLACE TRIGGER clients_update_trigger
    AFTER UPDATE ON clients
    FOR EACH ROW
    EXECUTE FUNCTION record_updated_client();
