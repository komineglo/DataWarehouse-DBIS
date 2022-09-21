

use DB6034059


--select * from product
--select * from order_item
--select * from customerorder


drop trigger if exists tr_insert_order_item
create trigger tr_insert_order_item on order_item instead of insert
as 
begin

-- Variablen fuer Relation Order_item
declare @order_id int
declare @product_id int 
declare @quantity int 
declare @order_item_price money
declare @status_code int
declare @product_price money
declare @total_price_new money 

select @product_id = product_id from inserted
select @product_price = product_price from product where product_id = @product_id 
select @order_id = order_id , @quantity= quantity ,
	   @status_code= status_code from inserted
select @order_item_price = @product_price * @quantity
insert into order_item values (@order_id, @product_id , @quantity, @order_item_price, @status_code)

select @total_price_new = sum (order_item_price) from order_item where order_id = @order_id
update customerorder set  total_price= @total_price_new where order_id = @order_id 

end

drop trigger if exists tr_delete_order_item
create trigger tr_delete_order_item on order_item instead of delete
as 
begin

-- Variablen fuer Relation Order_item
declare @order_id int
declare @product_id int 
declare @quantity int 
declare @order_item_price money
declare @status_code int
declare @product_price money
declare @total_price_new money 



select @order_id = order_id,  @product_id= product_id , @quantity= quantity , 
		@order_item_price= order_item_price , @status_code= status_code from deleted
delete from order_item where order_id =  @order_id and product_id =  @product_id 

select @total_price_new = sum (order_item_price) from order_item where order_id = @order_id
update customerorder set  total_price= @total_price_new where order_id = @order_id 

end



--------------------------------------------------------------------------------------------------------------------
