import { useParams } from 'react-router-dom';

export function ProductFormPage() {
  const { id } = useParams();
  return (
    <section>
      <h1>{id ? 'Modificar producto' : 'Crear producto'}</h1>
      <p>Admin-only product form goes here.</p>
    </section>
  );
}
