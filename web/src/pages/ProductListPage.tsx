import { useEffect, useState } from 'react';
import { api } from '../api/client';
import type { Category, Product } from '../api/types';
import { ProductCard } from '../components/ProductCard';
import './ProductListPage.css';

export function ProductListPage() {
  const [products, setProducts] = useState<Product[]>([]);
  const [categories, setCategories] = useState<Category[]>([]);
  const [categoryId, setCategoryId] = useState(0);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    api.get<Category[]>('/categories').then(setCategories).catch(() => {});
  }, []);

  useEffect(() => {
    setLoading(true);
    setError(null);
    const query = categoryId ? `?categoryId=${categoryId}` : '';
    api
      .get<Product[]>(`/products${query}`)
      .then(setProducts)
      .catch(() => setError('No se pudieron cargar los productos.'))
      .finally(() => setLoading(false));
  }, [categoryId]);

  return (
    <section>
      <h1>Nuestros productos</h1>

      <div className="product-list__filters">
        <button
          className={categoryId === 0 ? 'active' : ''}
          onClick={() => setCategoryId(0)}
        >
          Todas
        </button>
        {categories.map((category) => (
          <button
            key={category.id}
            className={categoryId === category.id ? 'active' : ''}
            onClick={() => setCategoryId(category.id)}
          >
            {category.name}
          </button>
        ))}
      </div>

      {loading && <p>Cargando productos...</p>}
      {error && <p className="product-list__error">{error}</p>}
      {!loading && !error && products.length === 0 && <p>No hay productos</p>}

      <div className="product-list__grid">
        {products.map((product) => (
          <ProductCard key={product.id} product={product} />
        ))}
      </div>
    </section>
  );
}
