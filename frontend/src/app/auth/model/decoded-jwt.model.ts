export class DecodedJwt {
  constructor(public role: string, public exp: number, public sub: string) {}
}
