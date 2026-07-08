import { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import { api } from '../api/client';
import type { Product } from '../api/types';
import './ProductDetailPage.css';

export function ProductDetailPage() {
  const { id } = useParams();
  const [product, setProduct] = useState<Product | null>(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    setLoading(true);
    setError(null);
    api
      .get<Product>(`/products/${id}`)
      .then(setProduct)
      .catch(() => setError('No se pudo cargar el producto.'))
      .finally(() => setLoading(false));
  }, [id]);

  if (loading) return <p>Cargando producto...</p>;
  if (error) return <p className="product-detail__error">{error}</p>;
  if (!product) return <p>Producto no encontrado.</p>;

  const inStock = product.stock.quantity > 0;

  return (
    <section className="product-detail">
      <img src={`/img/productos/${product.image}`} alt={product.name} />
      <div className="product-detail__info">
        <h1>{product.name}</h1>
        <p className="product-detail__price">{product.price.toFixed(2)}€</p>
        <p dangerouslySetInnerHTML={{ __html: product.description }} />
        {inStock ? (
          <p>Disponibles: {product.stock.quantity}</p>
        ) : (
          <p className="product-detail__out">Sin stock</p>
        )}
      </div>
    </section>
  );
}
