package de.hhn.it.pp.components.librarymanagementsystem.provider.librarymanagement;

public enum BookItemState {
    AVAILABLE,    // the book is available in the library
    LOANED,       // the book is not available, because it is borrowed
    LOST          // the book is not available, because the book was lost
}
