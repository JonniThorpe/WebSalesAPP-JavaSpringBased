import './Footer.css';

export function Footer() {
  return (
    <footer className="footer">
      <p>© {new Date().getFullYear()} The English Cut</p>
      <nav className="footer__links">
        <a href="/">Inicio</a>
        <a href="/productos">Productos</a>
        <a href="/login">Login</a>
      </nav>
    </footer>
  );
}
