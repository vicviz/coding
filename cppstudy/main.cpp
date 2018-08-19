#include <iostream>
#include "../basic/overload.h"

int main() {
    std::cout << "Hello, World!" << std::endl;
    std::cout<<sum(1, 2) << std::endl << sum(1, 2, 3);
    return 0;
}