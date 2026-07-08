import { useState } from 'react';
import { Link } from 'react-router-dom';
import './Navbar.css';

export function Navbar() {
  // TODO: wire to real auth/cart state once the REST session endpoints exist
  const [user] = useState<{ nombre: string } | null>(null);
  const cartCount = 0;

  return (
    <header className="navbar">
      <Link to="/" className="navbar__brand">The English Cut</Link>
      <nav className="navbar__links">
        <Link to="/">Inicio</Link>
        <Link to="/productos">Productos</Link>
        {user && <Link to="/pedidos">Pedidos</Link>}
      </nav>
      <div className="navbar__actions">
        <Link to="/carrito" className="navbar__cart">
          Carrito ({cartCount})
        </Link>
        {user ? (
          <>
            <span>{user.nombre}</span>
            <Link to="/logout">Salir</Link>
          </>
        ) : (
          <>
            <Link to="/login">Login</Link>
            <Link to="/register">Registro</Link>
          </>
        )}
      </div>
    </header>
  );
}
