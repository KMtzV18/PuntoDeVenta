Create database if not exists PuntoDeVenta;
use puntodeventa;
-- drop database puntodeventa;

create table Empleados(
id_empleado	int	not null	primary key unique	auto_increment,
nombre_completo	varchar(100)	not null,
usuario	varchar(20) not null unique,
password	varbinary(64) not null,
activo		tinyint(1) default 1 not null
);

-- drop table empleados;
-- corre este comando para tener un usuario k y contraseña 123:
-- insert into empleados values (null,'Kevin Martinez Vargas', 'k',sha2('123',256),1);


create table ventas(
id_venta	int not null	primary key auto_increment,
fecha datetime not null,
id_empleado int not null,
total double	not null,
foreign key (id_empleado) references Empleados(id_empleado)
);

-- drop table ventas;


create table productos(
id_producto int not null primary key auto_increment,
codigo Varchar(13) not null unique,
nombre varchar(20) not null,
descripcion varchar(50),
precio double not null,
stock int not null,
stockMinimo int ,
costo double,
descontinuado tinyint(1) default 0 not null
);


create table detalle_ventas(
id_detalle	int not null	primary key	auto_increment,
id_venta int not null,
id_producto int not null,
cantidad int not null,
precio_unitario double not null,
subtotal double not null,

foreign key (id_venta) references ventas(id_venta),
foreign key (id_producto) references productos(id_producto)
);


-- drop table detalle_ventas;
