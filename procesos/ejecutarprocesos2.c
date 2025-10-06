/*
exec cambia el proceso actual por el que se genera
es mas flexible y permite paso de argumentos
familia de funciones exec
execl
arg[0] es la ruta del ejecutable
siguietnes son los arguentos para el ejecutable
acaba en null
*/


#include <stdio.h>
#include <unistd.h>

int main() {
    char *url = "https://www.heraldo.es";

    printf("Abriendo navegador con exec...\n");

    // Reemplaza el proceso actual con 'xdg-open' pasando la URL
    execl("/usr/bin/xdg-open", "xdg-open", url, NULL);

    // Si execl falla
    perror("execl falló");
    return 1;
}
