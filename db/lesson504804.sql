create table products (
    id serial primary key,
    name varchar(50),
    producer varchar(50),
    count integer default 0,
    price integer
);

create or replace procedure insert_data(i_name varchar, prod varchar, i_count integer, i_price integer)
language 'plpgsql'
as $$
    BEGIN
    insert into products (name, producer, count, price)
    values (i_name, prod, i_count, i_price);
    END
$$;

create or replace procedure update_data(u_count integer, tax float, u_id integer)
language 'plpgsql'
as $$
    BEGIN
        if u_count > 0 THEN
            update products set count = count - u_count where id = u_id;
        end if;
        if tax > 0 THEN
            update products set price = price + price * tax;
        end if;
    END;
$$;

create or replace function f_insert_data(i_name varchar, prod varchar, i_count integer, i_price integer)
returns void
language 'plpgsql'
as
$$
    begin
        insert into products (name, producer, count, price)
        values (i_name, prod, i_count, i_price);
    end;
$$;

create or replace function f_update_data(u_count integer, tax float, u_id integer)
returns integer
language 'plpgsql'
as
$$
    declare
        result integer;
    begin
        if u_count > 0 THEN
            update products set count = count - u_count where id = u_id;
            select into result count from products where id = u_id;
        end if;
        if tax > 0 THEN
            update products set price = price + price * tax;
            select into result sum(price) from products;
        end if;
        return result;
    end;
$$;

create or replace procedure delete_data(u_count boolean, u_id integer)
language 'plpgsql'
as $$
    BEGIN
        if u_count = true THEN
            delete from products where count = 0;
        end if;
        if u_id > 0 THEN
            delete from products where id = u_id;
        end if;
    END;
$$;

create or replace function f_delete_data(u_count boolean, u_id integer)
returns integer
language 'plpgsql'
as
$$
    declare
        result integer;
    begin
        if u_count = true THEN
            delete from products where count = 0;
        end if;
        if u_id > 0 THEN
            delete from products where id = u_id;
        end if;
		select into result count(*) from products;
        return result;
    end;
$$;

