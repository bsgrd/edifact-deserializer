# treasurer-edifact

Library for deserialization of EDIFACT files (eg. CREMUL, FINSTA, DEBMUL)

## Automatic syntax detection (service segment)
If the first line contains a service segment in the following format, it will be parsed and used for deserialization

`UNA:+,? '`

Position 0-2 (UNA) = segment identifier

Position 3-4 (:) = composite data separator

Position 4-5 (+) = data element separator

Position 5-6 (,) = decimal notation

Position 6-7 (?) = escape character

Position 7-8 (empty) = reserved character - not used

Position 9-10 (') = segment terminator
