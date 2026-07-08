import { Link } from 'react-router-dom';
import type { Product } from '../api/types';
import './ProductCard.css';

interface ProductCardProps {
  product: Product;
}

export function ProductCard({ product }: ProductCardProps) {
  const inStock = product.stock.quantity > 0;

  return (
    <article className="product-card">
      <Link to={`/productos/${product.id}`}>
        <img src={`/img/productos/${product.image}`} alt={product.name} />
      </Link>
      <div className="product-card__body">
        <h3>{product.name}</h3>
        <p className="product-card__price">{product.price.toFixed(2)}€</p>
        {inStock ? (
          <p className="product-card__stock">Disponibles: {product.stock.quantity}</p>
        ) : (
          <p className="product-card__stock product-card__stock--out">Sin stock</p>
        )}
        <Link to={`/productos/${product.id}`} className="product-card__cta">
          Ver producto
        </Link>
      </div>
    </article>
  );
}
