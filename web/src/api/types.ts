// Mirrors App/src/main/java/com/theenglishcut/dto/*.java — keep in sync with the
// REST pass-through controllers once they're filled in, not with the JPA entities.

export interface Stock {
  id: number;
  quantity: number;
}

export interface Product {
  id: number;
  name: string;
  description: string;
  price: number;
  image: string;
  stock: Stock;
  categories: number[];
}

export interface Category {
  id: number;
  name: string;
  description: string;
}

export interface User {
  id: number;
  nombre: string;
  rol: { id: number; nombre: string };
}

export interface OrderLine {
  productId: number;
  productName: string;
  quantity: number;
}

export interface Order {
  id: number;
  usuario: string;
  productos: OrderLine[];
  fechaCreacion: string;
  entregaCompletada: boolean;
}
