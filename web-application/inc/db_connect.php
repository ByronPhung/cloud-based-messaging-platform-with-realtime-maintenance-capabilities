<?php
    include_once(dirname( __FILE__ ) . '/../aws/aws-autoloader.php');

    use Aws\Credentials\CredentialProvider;

    $profile = 'production';
    $path = dirname( __FILE__ ) . '/../inc/credentials.ini';

    $provider = CredentialProvider::ini($profile, $path);
    $provider = CredentialProvider::memoize($provider);

    $sdk = new Aws\Sdk([
      'region'      => 'us-west-2',
      'version'     => 'latest',
      'scheme'      => 'http', // Disable SSL for demo because we don't have a real certificate
      'credentials' => $provider
    ]);

    $dynamodb = $sdk->createDynamoDb();
?>