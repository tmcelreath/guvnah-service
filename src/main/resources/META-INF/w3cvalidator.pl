use WebService::Validator::HTML::W3C;

	my $v = WebService::Validator::HTML::W3C->new(
		detailed    => 1
    );
    if ( $v->validate_file(@ARGV) ) {
        if ( $v->is_valid ) {
             printf ("%s is valid\n", $v->uri);
        } else {
            printf ("%s is not valid\n", $v->uri);
            foreach my $error ( @{$v->errors} ) {
                printf("E|%d|%d|%s\n", $error->line,$error->col,$error->msg);
            }
            foreach my $warning ( @{$v->warnings} ) {
                printf("W|%d|%d|%s\n", $warning->line,$warning->col, $warning->msg);
            }
        }
    } else {
        printf ("EXCEPTION: %s\n", $v->validator_error);
    }
