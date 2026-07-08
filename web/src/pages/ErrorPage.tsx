import { useSearchParams } from 'react-router-dom';

export function ErrorPage() {
  const [params] = useSearchParams();
  const tipo = params.get('tipo') ?? 'unknown';

  return (
    <section>
      <h1>Error</h1>
      <p>Ha ocurrido un error ({tipo}).</p>
    </section>
  );
}
